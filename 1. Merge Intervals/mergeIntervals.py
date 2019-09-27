class Solution(object):
    def merge(self, intervals):
        """
        :type intervals: List[List[int]]
        :rtype: List[List[int]]
        """
        # edge cases
        if intervals is None or len(intervals) == 0 or len(intervals[0]) == 0:
            return None
        if len(intervals) == 1:
            return intervals
        
        # sort list first on first element of each list
        intervals.sort(key = lambda x:x[0])
        
        
        # create lower bound, upper bound, result list variables
        lower_bound = intervals[0][0]
        upper_bound = intervals[0][1]
        result = []
        
        
        # loop through intervals: 
        # for each list li, check first element against current upper bound. 
        # If li[0] <= upper bound, merge (update upper bound). 
        # Else: create list with lower/upper bounds, append to result, and update lower and upper bounds 
        for i in range(1, len(intervals)-1):
            if intervals[i][0] <= upper_bound and intervals[i][0] >= lower_bound:
                if intervals[i][1] > upper_bound:
                    upper_bound = intervals[i][1]
                if intervals[i][0] < lower_bound:
                    lower_bound = intervals[i][0]
            else:
                temp = [lower_bound, upper_bound]
                result.append(temp)
                lower_bound = intervals[i][0]
                upper_bound = intervals[i][1]

        # check for last list
        if intervals[-1][0] <= upper_bound and intervals[-1][0] >= lower_bound:
            if intervals[-1][1] > upper_bound:
                    upper_bound = intervals[-1][1]
            if intervals[-1][0] < lower_bound:
                    lower_bound = intervals[-1][0]
            result.append([lower_bound, upper_bound])
        else:
            result.append([lower_bound, upper_bound])
            result.append(intervals[-1])
            
        return result